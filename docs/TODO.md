
- Check where to handle errors (like database failed connections)
This is not being done right now

- Check again book and author stats services

- AuthorStats testing

------------------------------------------------------------------


- Create a BookInputHandler
  It will have methods for each validation to do (ex title, author name, etc)

  This is not okay considering the current code, but something like this

      private Author promptValidAuthor() {
          while (true) {
              System.out.print("Enter author name: ");
              String name = scanner.nextLine();
              Author author = new Author(name);
              ValidationResult result = authorValidator.validate(author);
              if (result.isValid()) {
                  return author;
              } else {
                  result.getErrors().forEach(System.out::println);
              }
          }
      }