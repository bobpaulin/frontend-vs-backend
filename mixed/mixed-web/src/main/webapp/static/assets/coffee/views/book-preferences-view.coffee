window.BookPreferencesView = class BookPreferencesView extends View

  template: Handlebars.templates['book-preferences']
  className: 'row-fluid'
  container: '#preferenceContainer'
  
  events: {
    'click #submit-preference' : 'submitPreference'
  }
  
  submitPreference:->
    @model.create({userName:@options.userName, keyword:$('[name="keyword"]').val()})
  
  render: ->
    super
    @model.forEach(@initItemView, this)
    this
  
  initItemView: (item) ->
    # Instantiate an item view
    currentView = new BookPreferenceView {model:item}
    $('.preferences').append(currentView.render().el)
    
  