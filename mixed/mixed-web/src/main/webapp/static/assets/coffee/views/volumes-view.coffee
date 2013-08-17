window.VolumesView = class VolumesView extends View

  template: Handlebars.templates['volumes']
  container: '#booksContainer'
  className: 'row-fluid'
  
  render: ->
    super
    @model.forEach(@initItemView, this)
    this
    
  initItemView: (item) ->
    # Instantiate an item view
    currentVolumesHtml = new VolumesHtml
    currentVolumesHtml.set 'keyword', item.get('keyword')
    currentSnippet = new SnippetView({container:'.volumes', model:currentVolumesHtml})
    currentVolumesHtml.fetch({
      dataType : 'html',
      success:(model,status) =>
        snippet = model.get 'snippet'
        currentSnippet.render()
    })