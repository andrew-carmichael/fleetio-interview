## Fleetio Assessment by Andrew Carmichael

Thank you for the opportunity to complete the Fleet.io take home interview.

![](screenshots/list_screen.png "List Screen")
![](screenshots/detail_screen.png "Detail Screen")

If you would like to try my submission in your browser without installing on an Android device, go [here](https://appetize.io/app/b_4jrcypyomazgbehiqudnwnjzom).

## Build instructions

Please note that API secrets are not commited to the GitHub repo. You will have to define the secrets
yourself in order to build the application.

Add to your gradle.properties file, or to your environment:

`
FLEETIO_ACCOUNT_TOKEN="secret"
FLEETIO_AUTHORIZATION_TOKEN="Token secret"                                                                       
GOOGLE_MAPS_API_KEY="secret"
`

The application was build and tested only with:
- Android Studio Ladybug Feature Drop | 2024.2.2 Patch 1
- Tested with Pixel 9 API 35 emulator

## Notable facts
- Application implemented following Google recommended Android architecture (clean architecture).
- Application is completely dependency injected using Koin.
- Both screens in the app have loading and error states.
- The list screen is implemented with paging, including loading more indication.
- The detail screen will display a map of the vehicle's last known location if defined.
