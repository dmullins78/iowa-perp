import net.miginfocom.swing.MigLayout

application(title:'Predator', size:[400,100], locationByPlatform:true, iconImage: imageIcon('/ateam5_128.png').image) {

    panel(layout:new MigLayout("wrap 1","[] 16 []")) {
        textField(columns:25, constraints:'split 2', text:bind(target:model, targetProperty:'searchValue'))
        comboBox(id:'searchBy', model: bind {model.searchFields})

        button(id:'download', text:'Search', constraints:'wrap', actionPerformed:{controller.search()})
    }

}

