(ns datafab.mailreloader
  (:import (javax.servlet Filter FilterChain FilterConfig
                          ServletRequest ServletResponse))
  (:require [ns-tracker.core :refer :all]
            [clojure.tools.logging :as log :only [trace debug error info]]
            #_[clojure.tools.namespace.repl :refer [refresh]]))

(println "running datafab.mailreloader")

(defn -init [^Filter this ^FilterConfig cfg])

(defn -destroy [^Filter this])

;; For testing :ear:aRun: the paths to watch are relative to main/ear/build/exploded-app
;; make them match what's in modules/ear/src/main/application/META-INF/application.xml
;;  and modules/ear/src/main/application/META-INF/application.xml
(defonce main-namespaces (ns-tracker ["./mod-main-0.1.0/WEB-INF/classes"]))

;; For testing :mod-main:aRun: the paths to watch are relative to main/mod-main/build/exploded-app
;;(def main-namespaces (ns-tracker ["./build/classes/WEB-INF/classes/main/main"]))

(defn -doFilter
  [^Filter this
   ^ServletRequest rqst
   ^ServletResponse resp
   ^FilterChain chain]
  (log/trace "running datafab.reloader doFilter")
  (println "RUNNING datafab.mailreloader doFilter")
  (require '(datafab mailhandler) :reload)
  (.doFilter chain rqst resp))

;; (clojure.core/println "loading mod-main main.reloader")
