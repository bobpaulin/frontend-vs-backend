if not $.cookie('userName')?
  $.cookie 'userName', 'bpaulin'

user = new User()
  
userLoggedInView = new UserLoggedInView({model: user})

if window.location.pathname is '/mixed-web/cached'

  bookPreferences = new BookPreferences()

  bookPreferencesView = new BookPreferencesView({model: bookPreferences})

  volumesView = new VolumesView({model:bookPreferences})
    
  bookPreferences.fetch()

else if window.location.pathname.match /^\/mixed-web\/cached\/review/
  
  bookId = window.location.pathname.replace('/mixed-web/cached/review/', '')
  messages = new Messages([], {bookId: bookId })

  messagesView = new MessagesView({model:messages})

  messages.fetch()
  
  #This needs to fire after document is ready.
  $(document).ready ->
    formModel = new Backbone.Model({userName: $.cookie('userName'), bookId: bookId})
    messageFormView = new MessageFormView({bookId: bookId, messages: messages, model:formModel})
  