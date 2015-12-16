(defproject datafab "0.0.1-SNAPSHOT"
  :source-paths ["mod-main/src/main/clojure"]
                 ;; "mod-main/src/main/webapp"]
                 ;; "mod-main/src/main/webapp/styles"
                 ;; "mod-main/src/main/webapp/themes"]
;;                 "checkouts/miraj/src/clj"]


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
  :dependencies [[org.clojure/clojure "1.8.0-RC3"] ;; "1.7.0"]
                 [org.clojure/core.async "0.2.374"]
                 [org.clojure/clojurescript "1.7.170"]
                 [polymer/iron "1.2.3-SNAPSHOT"]
                 [polymer/neon "1.2.3-SNAPSHOT"]
                 [polymer/paper "1.2.3-SNAPSHOT"]
                 [polymer.elements/support "1.2.3-SNAPSHOT"]
                 [miraj/polyfills "0.7.19-SNAPSHOT"]
                 [miraj "1.1.4-SNAPSHOT"]
                 [cheshire "5.5.0"]
                 [compojure "1.4.0"]
                 [ring/ring-core "1.4.0"]
		 [ring/ring-defaults "0.1.5"]
                 [ring/ring-headers "0.1.3"]
                 [com.google.appengine/appengine-api-1.0-sdk "1.9.26"]
                 [com.google.appengine/appengine-api-labs "1.9.27"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.slf4j/slf4j-log4j12 "1.7.1"]
                 [log4j/log4j "1.2.17" :exclusions [javax.mail/mail
                                                    javax.jms/jms
                                                    com.sun.jmdk/jmxtools
                                                    com.sun.jmx/jmxri]]
                 [clj-logging-config "1.9.7"]
                 ]
  :profiles {:dev {:prep-tasks ^:replace ["clean" "compile"]
                   :source-paths ["src/clj" "test" "dev"]
                   :resource-paths ["resources/public"]
                   :dependencies [[ring "1.4.0"]
                                  [potemkin "0.4.1"]
                                  [slingshot "0.12.2"]
                                  [org.clojure/tools.namespace "0.2.11"]]
                    }}
  :repl-options {:port 4001}
  :plugins [[lein-ring "0.8.13"]
            [lein-cljsbuild "1.1.0"]]
  :ring {:handler miraj/start :init config/init :port 8087}
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
  :auto {:default {:file-pattern #"\.(clj|css|js)$"}}
  :resource {
             :resource-paths [
                              ;; ["mod-main/src/main/webapp"
                              ;;  {:target-path "ear/build/exploded-app/mod-main-0.1.0"
                              ;;   :includes [ #".*.css"]}]
                              ;; ["mod-main/src/main/webapp"
                              ;;  {:target-path "ear/build/exploded-app/mod-main-0.1.0"
                              ;;   :includes [ #".*.html"]
                              ;;   :excludes [ #".*polymer/.*"]}]
                              ["mod-main/src/main/clojure"
                               {:target-path "ear/build/exploded-app/mod-main-0.1.0/WEB-INF/classes"
                                :includes [ #".*.clj" ]
                                :excludes [ #".*~" ]}]
                              ["checkouts/miraj/src/clj"
                               {:target-path "ear/build/exploded-app/mod-main-0.1.0/WEB-INF/classes"
                                :includes [ #".*.clj" ]
                                :excludes [ #".*~" ]}]]
                              :update false      ;; if true only process files with src newer than dest
             :silent false
             :verbose false
             :skip-stencil [ #".*" ]
             })

