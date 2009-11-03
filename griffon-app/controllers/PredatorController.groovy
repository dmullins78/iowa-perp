class PredatorController {

    def model
    def view
    def sexOffenderScraper

    def onStartupEnd = { app ->
        sexOffenderScraper = new SexOffenderScraper()
    }

    def search = {
        def searchType = (String)view.searchBy.getSelectedItem()
        println("Searching for ${model.searchValue} by ${searchType}")
        doOutside {
            sexOffenderScraper.search(model.searchValue, searchType).each { offender ->
                println("Found ${offender.name}")
            }

        }
    }


}

