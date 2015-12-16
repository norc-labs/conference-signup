;;(println "starting load of config.clj")
(ns config
  (:require [miraj :refer [>> >>! start]]
            [miraj.http.response :refer [not-found]]
            [miraj.markup :as mk]
            [clojure.pprint :as pp]
            [ring.util.servlet :as servlet])
  (:import [javax.servlet ServletConfig]
           [java.io ByteArrayInputStream StringReader StringWriter]))

;;(println "LOADING config")

;; this is for lein-ring:
;; (defn initialize [] (println "RUNNING config/initialize"))

(miraj/config :sync)

;; for servlets
;;FIXME - figure out how to hide this in miraj.clj
(defn -init
  ([this]
   #_(println "Servlet Init A called"))
  ([this sc]
   (.initParent this sc)
   #_(println "Servlet Init B called")))

(servlet/defservice miraj.sync/start)
;; (servlet/make-service-method msync/start))

 ;; #_(miraj/config :sync))

;; (println "config DONE, requiring datafab.core")

(require '[datafab.core])

;; (defn pprint-str [m]
;;   (let [w (StringWriter.)] (pp/pprint m w)(.toString w)))

;; now do configure-namespace?

;;(>> dev datafab.core/main)

(>> application datafab.core/application) ;; #(datafab.core/apply %))

;;(>> serialize datafab.core/serialize)

;; (spit "datafab.html"
;;       (with-out-str
;;         (miraj.markup/pprint :html
;;                              (miraj.sync/activate 'datafab.core/main))))

;; (println (miraj.markup/pprint :html (datafab.core/main)))

;; (spit "datafab.html"
;;       (with-out-str
;;         (miraj.markup/pprint :html (miraj.sync/activate 'datafab.core/main))
;; )))

;;(>> dump datafab.core/dump)

(>> _ah.mail datafab.mailhandler/handler)

(>> * #(not-found (str "not found: " (:uri %))))

;; HEAD
;; (>>? $ hello.world/main)

;; POST - definitely mutational
;;(>>! foo datafab.core/f1!)

;; (println "config load complete")
