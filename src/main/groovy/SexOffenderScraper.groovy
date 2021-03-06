class SexOffenderScraper {

    def search(searchValue, searchBy) {
        def myDocument = new XmlParser( new org.cyberneko.html.parsers.SAXParser() ).parse(buildUrl(searchValue, searchBy))

        def tmpList = []
        myDocument.depthFirst().A.findAll{ it['@class'] =~ 'results' }.each {
	        def id = it.parent().INPUT.find{ it['@name'] == 'id'}.@value
	        def addresses = it.parent().parent().parent().TD[1].text().split(",")

            def city = addresses[addresses.length - 1].trim().replaceAll("IA", "")
            tmpList << [id: id, name: it.text(), city: city]
        }

        return tmpList
    }

    def buildUrl(searchValue, searchBy) {
        def url = "http://www.iowasexoffender.com/searchENG.php?countysearch=ALL&consearch=ALL&simple=submit&"
        return searchBy == 'Last Name' ? url + "lname=${java.net.URLEncoder.encode(searchValue)}" : url + "city=${java.net.URLEncoder.encode(searchValue)}"
    }

    def details(id) {
        def myDocument = new XmlParser( new org.cyberneko.html.parsers.SAXParser() ).parse("http://www.iowasexoffender.com/sho.php?id=${id}")

        def name = getValue(myDocument, "Name")
        def county = getValue(myDocument, "County")
        def address = getValue(myDocument, "Address")
        def image = "http://www.iowasexoffender.com/" + myDocument.depthFirst().IMG.findAll {it['@name'] == "imgconvict"}.'@src'[0]

        return [name: name, address: address, county:county, image:image]
    }

    def getValue(myDocument, field) {
	    return myDocument.depthFirst().B.find { it =~ field }.parent().parent().TD[1].FONT.text()
    }


}

