module.exports = (match) ->
  match '', 'home#index'
  match 'review/:bookId', 'home#reviewPage'
