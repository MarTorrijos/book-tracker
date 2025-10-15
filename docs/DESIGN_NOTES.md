# Design notes

As a backend developer, I’m used to working with certain tools and patterns. Like Spring Boot, DTOs, and layered architectures. But for this project, I intentionally skipped some of those to keep things simple and aligned with the actual needs of the app.

Here’s a quick look at a few things I chose not to use, and why:


### No Spring Boot

This program has no web interface, no need for REST APIs and isn’t meant to be deployed or scaled. It’s just a simple app that runs locally.

Spring Boot would have made connecting to the database easier, but it also would have added unnecessary complexity: extra dependencies, configuration and a heavier runtime. I didn’t need an embedded server or auto-configuration. The MongoDB Java driver was more than enough for the CRUD operations, sorting and stats I needed.

Spring Boot felt like overkill. For a small tool like this, something lightweight and minimal made more sense.


### No DTOs

I didn’t use DTOs in this project because there was no real need for them. It’s a simple, local CLI-based Java app with no API, no external consumers, and no complex data transformations between layers.

Using DTOs would’ve just added unnecessary boilerplate without solving any actual problem. Following KISS and YAGNI, keeping things simple made more sense — especially since the domain model and the data I work with are essentially the same.