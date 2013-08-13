messages = new Messages([], {bookId:'RhuT2nZayP4C'})

messagesView = new MessagesView({model:messages})

messages.fetch()

bookPreferences = new BookPreferences([], {userName:'bpaulin'})

bookPreferencesView = new BookPreferencesView({model: bookPreferences})

bookPreferences.fetch()