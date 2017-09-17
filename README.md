Simple application to find info about Marvel World characters

        Marvel official API used to find data about characters.(developer.marvel.com)

        App searches characters by the first letters of user input.

        Then user is able to see character's details if present.
        They are :
        - main image in high resolution
        - some short description
        - list of comics character has participated
        - comics preview when tapped on the comics nomination (1 or more pics available to see by swiping)
        - outer links to go and see in browser some more info

        also app saves search results so you can go back in stack and see them.


        ![marvelworld1](https://user-images.githubusercontent.com/29121233/30524782-a7b01b28-9c02-11e7-81a1-7e25b72d384b.gif)
        ![marvelworld2](https://user-images.githubusercontent.com/29121233/30524783-aa9cdaf6-9c02-11e7-9f87-ce9b4c80efcc.gif)


        Libs used in project:
        1.Retrofit (http://square.github.io/retrofit/) used to turn our HTTP API into a Java interface.
        2.Jackson (https://github.com/FasterXML/jackson) used to integrate JSON with Java.
        3.Guava (https://github.com/google/guava) set of core libraries.
        4.Picasso (http://square.github.io/picasso/) used for powerful image downloading and caching.
