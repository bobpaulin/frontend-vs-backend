
if not $.cookie('userName')?
  $.cookie 'userName', 'bpaulin'

if window.location.pathname is '/mixed-web/main'
  bookPreferences = new BookPreferences()

  bookPreferencesView = new BookPreferencesView({model: bookPreferences})

  bookPreferences.fetch()

else if window.location.pathname.match /^\/mixed-web\/main\/review/
  
  bookId = window.location.pathname.replace('/mixed-web/main/review/', '')
  messages = new Messages([], {bookId: bookId })

  messagesView = new MessagesView({model:messages})

  messages.fetch()
  
  new MessageFormView({bookId: bookId, model: messages})