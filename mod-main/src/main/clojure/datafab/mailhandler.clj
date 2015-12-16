(ns datafab.mailhandler
  (:require [clojure.tools.logging :as log :only [trace debug error info]]
            [clojure.pprint :as pp]
            [clojure.string :as string]
            [miraj.http.response :refer [ok created]]
            [ring.util.servlet :as servlet])
  (:import [javax.servlet ServletConfig]
           [com.google.appengine.api.mail MailService]
           [java.util Properties]
           [javax.mail Message Message$RecipientType MessagingException Transport]
           [javax.mail Session]
           [javax.mail.internet AddressException InternetAddress MimeMessage]))

(log/trace "loading co-ns datafab.mailhandler")

;; (println "datafab.core metadata: " (meta (find-ns 'datafab.core)))


(defn handler
  [msg]
  (println "mailhandler")
  (println "INCOMING MSG: " msg)
  (ok))

(servlet/defservice handler)
