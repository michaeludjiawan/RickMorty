# RickMorty
Rick and Morty API Implementation Demo

## Overview
- Using MVVP + Repository Pattern
- DI: Koin
- Network: Retrofit
- Concurrency: Coroutines & LiveData

## API Usages
- Get All Characters (https://rickandmortyapi.com/api/character)
- Get multiple locations (https://rickandmortyapi.com/api/location/[ids])
  - Used to get origin and location info of a character
- Get multiple episodes (https://rickandmortyapi.com/api/episode/[ids])
  - Used to get all episodes that a character appeared

## Implementation Notes
- Provide basic UI and Unit Tests
- Data is not saved to persistent storage

## Screenshots
<img src="https://github.com/michaeludjiawan/RickMorty/blob/master/screenshots/screen_home.png" width="300">
<img src="https://github.com/michaeludjiawan/RickMorty/blob/master/screenshots/screen_detail_loading.png" width="300">
<img src="https://github.com/michaeludjiawan/RickMorty/blob/master/screenshots/screen_detail1.png" width="300">
<img src="https://github.com/michaeludjiawan/RickMorty/blob/master/screenshots/screen_detail2.png" width="300">
