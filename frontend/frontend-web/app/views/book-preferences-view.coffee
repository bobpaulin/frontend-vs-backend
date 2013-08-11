template = require 'views/templates/book-preferences'
CollectionView = require 'views/base/collection-view'
BookPreferenceView = require 'views/book-preference-view'

module.exports = class BookPreferencesView extends CollectionView

  template: template
  container: '#preferenceContainer'
  className: 'row-fluid'
  autoRender: true
  listSelector: '.preferences'
  
  events: {
    'click #submit-preference' : 'submitPreference'
  }
  
  submitPreference:->
    @model.create({userName:@options.userName, keyword:$('[name="keyword"]').val()})
  
  initialize:->
    super
    
  doModelChange: ->
    # re render view now
    @render()
  
  initItemView: (item) ->
    # Instantiate an item view
    new BookPreferenceView {model:item, autoRender: true}
    
  