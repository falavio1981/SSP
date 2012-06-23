Ext.define('Ssp.view.tools.journal.EditJournal',{
	extend: 'Ext.form.Panel',
	alias : 'widget.editjournal',
    mixins: [ 'Deft.mixin.Injectable',
              'Deft.mixin.Controllable'],
    controller: 'Ssp.controller.tool.journal.EditJournalViewController',
    inject: {
        confidentialityLevelsStore: 'confidentialityLevelsStore',
        journalSourcesStore: 'journalSourcesStore',
        journalTracksStore: 'journalTracksStore',
        model: 'currentJournalEntry'
    },	
    initComponent: function() {
    	Ext.applyIf(this, {
        	title: ((this.model.get('id') == "") ? "Add Journal" : "Edit Journal"),
        	autoScroll: true,
        	defaults: {
            	labelWidth: 150,
            	padding: 5
            },
        	items: [
                {
			        xtype: 'combobox',
			        itemId: 'confidentialityLevelCombo',
			        name: 'confidentialityLevelId',
			        fieldLabel: 'Confidentiality Level',
			        emptyText: 'Select One',
			        store: this.confidentialityLevelsStore,
			        valueField: 'id',
			        displayField: 'name',
			        mode: 'local',
			        typeAhead: true,
			        queryMode: 'local',
			        allowBlank: false,
			        forceSelection: true,
			        anchor: '100%'
				},{
			        xtype: 'combobox',
			        itemId: 'journalSourceCombo',
			        name: 'journalSourceId',
			        fieldLabel: 'Source',
			        emptyText: 'Select One',
			        store: this.journalSourcesStore,
			        valueField: 'id',
			        displayField: 'name',
			        mode: 'local',
			        typeAhead: true,
			        queryMode: 'local',
			        allowBlank: false,
			        forceSelection: true,
			        anchor: '100%'
				},{
			        xtype: 'combobox',
			        itemId: 'journalTrackCombo',
			        name: 'journalTrackId',
			        fieldLabel: 'Journal Track',
			        emptyText: 'Select One',
			        store: this.journalTracksStore,
			        valueField: 'id',
			        displayField: 'name',
			        mode: 'local',
			        typeAhead: true,
			        queryMode: 'local',
			        allowBlank: true,
			        forceSelection: false,
			        anchor: '100%'
				},{
		        	xtype: 'label',
		        	text: 'Session Details (Critical Components)'
				},{
					xtype: 'tbspacer',
					flex: 1
				},{
		            tooltip: 'Add Journal Session Details',
		            text: 'Add/Edit Session Details',
		            xtype: 'button',
		            itemId: 'addSessionDetailsButton'
	    	    },
                { xtype: 'displayjournaldetails', autoScroll: true, anchor:'100% 50%' }
				,{
                    xtype: 'textareafield',
                    fieldLabel: 'Comment',
                    itemId: 'commentText',
                    anchor: '100%',
                    name: 'comment',
                    anchor: '100%'
                }],
            
            dockedItems: [{
       		               xtype: 'toolbar',
       		               items: [{
		       		                   text: 'Save',
		       		                   xtype: 'button',
		       		                   action: 'save',
		       		                   itemId: 'saveButton'
		       		               }, '-', {
		       		                   text: 'Cancel',
		       		                   xtype: 'button',
		       		                   action: 'cancel',
		       		                   itemId: 'cancelButton'
		       		               }]
       		           }]
        });

        return this.callParent(arguments);
    }	
});