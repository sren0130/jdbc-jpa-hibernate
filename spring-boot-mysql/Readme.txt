This project is from spring io.

POST methdo uses @RequestParameter
       public @ResponseBody String addNewUser (@RequestParam String name
               , @RequestParam String email) { ....}

That means input values are in query strings!              FKKK!!!

Content-Type is not required for query string.


I create another POST method, use request body as input.
    // value = "/post/user", is a must, it should always be name=value format.
    // Accept application/json is required in postman
    @PostMapping(value = "/post/user",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<User> postBody(@RequestBody User user) {
        User returnUser = userRepository.save(user);

        return ResponseEntity.created(URI.create(String.format("/users/%s", returnUser.getName())))
                .body(returnUser);

    }

I use postman,
http://localhost:8090/demo/post/user
    it works when Content-Type      Applicaiton/json
                Accept          Application/json

    Body has
    {
        "name":"John",
        "email":"john@foo.com"
    }

    =============================


How to use curl
GET
curl -X GET "http://localhost:8090/demo/all"

POST
curl -H "Content-Type: application/json" --request POST --data "{"name":"CurlMan", "email":"CurlMan@yahoo.com"}' http://localhost:8090/demo/post/user


Windows specialty
For windows, single quotes around json did not work and I ended up escaping double quotes.

curl -X POST -H "Content-Type: application/json" -d "{ \"key1\": \"value1\" }" http://localhost:3000/api/method

curl -X POST http://localhost:8090/demo/post/user -H "Content-Type: application/json" -d "{\"name\": \"Curlman\", \"email\": \"Curlman@yahoo.com\"}"

Above DOES NOT work.???????????????

Arguments in query string post

curl -X POST http://localhost:8090/demo/add -H "Content-Type: application/json" -a "name=Curlman&email=curlman@yahoo.com"