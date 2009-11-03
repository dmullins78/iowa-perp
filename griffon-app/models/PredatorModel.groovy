import groovy.beans.Bindable

class PredatorModel {

    @Bindable searchValue
    @Bindable searchFields = new javax.swing.DefaultComboBoxModel(['Last Name', 'City'] as Object[])

}

