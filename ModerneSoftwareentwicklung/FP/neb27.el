(require 'dom)
(let ((dom (with-current-buffer (url-retrieve-synchronously "https://www.lb-neb.de/de/app/webtools/trains.widget?action=departure&stop=1510837020596")
	     (libxml-parse-html-region (point-min) (point-max)))))
  
  (format "nächste Abfahrt um %1$s nach %2$s"

	  (string-trim (dom-text ;;nächste Abfahrt um
			(nth 2
			     (dom-children(nth 2(dom-children(dom-by-class dom "ids-table ids-table-striped"))))
			     )))
	  
	  (string-trim (dom-text ;;nächste Abfahrt nach
			(nth 4 (dom-children (nth 4
						  (dom-children(nth 2(dom-children(dom-by-class dom "ids-table ids-table-striped"))))
						))))))
  )
