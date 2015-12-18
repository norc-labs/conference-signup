(ns datafab.servlets)

(gen-class :name config
           :extends javax.servlet.http.HttpServlet
           :exposes-methods {init initParent}
           :impl-ns config)

(gen-class :name datafab.mailhandler
           :extends javax.servlet.http.HttpServlet
           :impl-ns datafab.mailhandler)

(gen-class :name datafab.reloader
           :implements [javax.servlet.Filter]
           :impl-ns datafab.reloader)

(gen-class :name datafab.mailreloader
           :implements [javax.servlet.Filter]
           :impl-ns datafab.mailreloader)
