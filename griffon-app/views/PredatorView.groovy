import net.miginfocom.swing.MigLayout

import ca.odell.glazedlists.*
import ca.odell.glazedlists.gui.*
import ca.odell.glazedlists.swing.*

import javax.swing.event.*

def createTableModel() {
   def columnNames = ["Id", "Name", "City"]
   new EventTableModel(model.personsList, [
          getColumnCount: {columnNames.size()},
          getColumnName:  {index -> columnNames[index]},
          getColumnValue: {object, index ->
             object."${columnNames[index].toLowerCase()}"
          }] as TableFormat)
}

application(title:'Predator', size:[700,435], locationByPlatform:true, iconImage: imageIcon('/ateam5_128.png').image) {

    panel (layout:new MigLayout()) {
        panel(layout:new MigLayout("wrap 1","[] 16 []")) {
            textField(columns:25, constraints:'split 2', text:bind(target:model, targetProperty:'searchValue'))
            comboBox(id:'searchBy', model: bind {model.searchFields})

            button(id:'download', text:'Search', constraints:'wrap', actionPerformed:{controller.search()})

            scrollPane (maximumSize:[400, 700]) {
                table(id: "personsTable", model: createTableModel())
                new TableComparatorChooser(personsTable,
                    model.personsList, AbstractTableComparatorChooser.SINGLE_COLUMN)
            }
        }
        panel(layout:new MigLayout("wrap 1","[] 16 []")) {
            label(text:bind(target:model, targetProperty:'perpName', mutual:true))
            label(text:bind(target:model, targetProperty:'perpAddress', mutual:true))
            label( icon:bind { model.picture })
        }
    }

    personsTable.selectionModel.addListSelectionListener([
        valueChanged: { event ->
            if (event.valueIsAdjusting){
                def selectedIndex = personsTable.selectedRow
                controller.getDetails(personsTable.model.getValueAt(selectedIndex, 0))
            }
        }
    ] as ListSelectionListener )

}

