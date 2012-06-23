Ext.define('Ssp.view.person.ReferralSources', {
	extend: 'Ext.form.Panel',
	alias: 'widget.personreferralsources',
	mixins: [ 'Deft.mixin.Injectable',
              'Deft.mixin.Controllable'],
    controller: 'Ssp.controller.person.ReferralSourcesViewController',
    inject: {
    	store: 'referralSourcesBindStore'
    },
	width: '100%',
    height: '100%',
    autoScroll: true,
	initComponent: function() {	
		Ext.apply(this, 
				{
				    bodyPadding: 5,
				    layout: 'anchor',
				    items:[{
			            xtype: 'itemselectorfield',
			            name: 'referralSourceIds',
			            itemId: 'referralSourcesItemSelector',
			            anchor: '100%',
			            height: 200,
			            fieldLabel: 'Referral Sources',
			            store: this.store,
			            displayField: 'name',
			            valueField: 'id',
			            allowBlank: true,
			            flex: 1
			        }]
				});	
		
		return this.callParent(arguments);
	}
});