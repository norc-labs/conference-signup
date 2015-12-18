(ns datafab.mailhandler
  (:require [clojure.tools.logging :as log :only [trace debug error info]]
            [clojure.pprint :as pp]
            [clojure.string :as str]
            [miraj.http.response :refer [ok created]]
            [ring.middleware.keyword-params :refer [keyword-params-request]]
            [ring.middleware.params :refer [assoc-form-params params-request]]
            [ring.util.servlet :as servlet])
  (:import [javax.servlet ServletConfig]
           [com.google.appengine.api.mail MailService]
           [java.util Properties]
           [javax.mail Message Message$RecipientType MessagingException Transport]
           [javax.mail Session]
           [javax.mail.internet AddressException InternetAddress MimeMessage]))


;; (println "datafab.core metadata: " (meta (find-ns 'datafab.core)))


(defn application
  ;; [^:? applicant-name ^:? affiliation
  ;;  ^:? address1 ^:? address2 ^:? city ^:? state  ^:? zip
  ;;  ^:? email
  ;;  ^:? interest ^:? comments]
  [{:keys [applicant-name affiliation
    address1 address2 city state zip
    email
    interest comments]}]
  (let [id (rand-int 1000000)
        hdr (str "#ID,applicant-name,address1,address2,city,state,zip,email,affiliation,interest,comments")
        body (str/join "," [id applicant-name address1 address2 city state zip email affiliation interest comments])
        msg-body (str hdr "\n" body)]
    ;; (println "MSG:")
    ;; (println msg-body)
    (let [props (java.util.Properties.)
          session (javax.mail.Session/getDefaultInstance props nil)]
      (try
        (let [msg (doto (MimeMessage. session)
                    (.setFrom (InternetAddress. "admin@datafab2016.appspotmail.com" "DataFab2016 Admin"))
                    (.addRecipient Message$RecipientType/TO
                                   (InternetAddress. "norc.datafab2016@gmail.com", "DataFab2016 Admin"))
                    (.setSubject "datafab2016 new application")
                    (.setText msg-body))]
          (Transport/send msg))
        (catch AddressException e
          (log/trace "AddressException: " (.getMessage e)))
        (catch MessagingException e
          (log/trace "MessagingException: " (.getMessage e))))
      (log/info "SENT EMAIL " id))
    (ok)))

(defn handler
  [rqst]
  ;; (log/info "mailhandler")
  ;; (println "mailhandler")
  ;; (println "INCOMING MSG: " rqst)
  (if (= (:request-method rqst) :post)
    (let [form-params (assoc-form-params rqst "UTF-8")
          params (params-request form-params)
          args (keyword-params-request params)]
      (application (:params args))
      (ok))))

(servlet/defservice handler)
