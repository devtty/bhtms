#!/usr/bin/emacs --script

(require 'dom)

(defun nebtable (dom)
  (dom-children(nth 2(dom-children(dom-by-class dom "ids-table ids-table-striped")))))

(let ((dom (with-current-buffer (url-retrieve-synchronously "https://www.lb-neb.de/de/app/webtools/trains.widget?action=departure&stop=1510837020596")
	     (libxml-parse-html-region (point-min) (point-max)))))
  
  (print (format "nächste Abfahrt um %1$s nach %2$s"

	  (string-trim (dom-text (nth 2 (nebtable dom)))) ;;nächste Abfahrt um
	  
	  (string-trim (dom-text (nth 4 (dom-children (nth 4 (nebtable dom))))))  ;;nächste Abfahrt nach

	  )))
