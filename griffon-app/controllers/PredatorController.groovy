class PredatorController {

    def model
    def view
    def sexOffenderScraper

    def onStartupEnd = { app ->
        sexOffenderScraper = new SexOffenderScraper()
    }

    def search = {
        def searchType = (String)view.searchBy.getSelectedItem()
        doOutside {
            def tmpList = sexOffenderScraper.search(model.searchValue, searchType)
            doLater {
                model.personsList.clear()
                model.personsList.addAll(tmpList)
            }
        }
    }


}

