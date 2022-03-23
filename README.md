## Management API Automation

This repository contains a set of snippets using Adyen's [Management API](https://docs.adyen.com/api-explorer/#/ManagementService/v1/overview) that we Developer Advocates use to automate parts of our workflows.

* You can read an introduction about the Management API [on our blog](https://www.adyen.com/blog/automate-workflows-with-management-api).
* The complete description of our Management API is available on the [API Explorer](https://docs.adyen.com/api-explorer/#/ManagementService/v1/overview).

## Prerequisites and Intro

* To use these snippets, you need to have a [test account](https://authn-test.adyen.com/authn/ui/login).
* Those snippets are written in [Kotlin](https://kotlinlang.org/).

### API Keys

To use these snippets, You need to [generate an API KEY](https://docs.adyen.com/development-resources/api-credentials) and make it available as environment variable under the name MANAGEMENT_KEY.

The snippets will not all need the same roles. You can see a complete list of available roles [here](https://docs.adyen.com/development-resources/api-credentials/roles).
Typically, you will need roles of the "Management API" type.


## Available snippets

* [CreateCompanyUser](./src/main/kotlin/me/adyen/CreateCompanyUser.kt) - Creates a User on the test environment with a set of rights. We use that snippet to create accounts for our candidates so they can do the technical interview.
  * Required role to run the snippet - "Management API—Users read and write" 

## Contributing

We commit all our new features directly into our GitHub repository. Feel free to request or suggest new features or code changes yourself as well!

Find out more in our [Contributing](https://github.com/adyen-examples/.github/blob/main/CONTRIBUTING.md) guidelines.

## License

MIT license. For more information, see the [LICENSE](./LICENSE.md) file in the root directory.