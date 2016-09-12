#Word Density

This project makes use of the HTML Searcher I previously built.
The program will request a page. Scan the content and return the most common words found.

As the request returns HTML and not just text the program aims to filter out as much noise as possible.

##To run:
	from command line do the following:
	 `java -jar thisJar url howManyResults minimumResultLength`
- url -- This is the page that will be searched include
- howManyResults -- this determines how many words are returned
- minimumResultLength -- this determines how long words must be
