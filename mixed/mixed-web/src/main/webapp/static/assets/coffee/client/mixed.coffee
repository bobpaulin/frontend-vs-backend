
if window.location.pathname is '/mixed-web/main'
  bookPreferences = new BookPreferences()

  bookPreferencesView = new BookPreferencesView({model: bookPreferences})

  bookPreferences.fetch()

else if window.location.pathname.startsWith '/mixed-web/main/review'
  
  
  messages = new Messages([], {bookId: window.location.pathname.replace('/mixed-web/main/review/', '') })

  messagesView = new MessagesView({model:messages})

  messages.fetch()