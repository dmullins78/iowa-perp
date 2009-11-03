import groovy.beans.Bindable
import ca.odell.glazedlists.EventList
import ca.odell.glazedlists.BasicEventList
import ca.odell.glazedlists.SortedList

class PredatorModel {

    @Bindable searchValue
    @Bindable searchFields = new javax.swing.DefaultComboBoxModel(['Last Name', 'City'] as Object[])

    EventList personsList = new SortedList(new BasicEventList(),
     {a, b -> a.id <=> b.id} as Comparator )
}

