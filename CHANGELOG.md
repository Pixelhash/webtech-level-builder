# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

## [Unreleased]
### Added

### Changed

## [1.2.0] - 2018-06-13
### Added
- Added fox as tile type with an option to set its movement type.
- Added a new level input field called `description`.
- Added a menu with possibility to create and load a level as well as an exit button.

### Changed
- The "Start" tile is know called "Rabbit" and has the background color white instead of blue.
- Revamped GUI.
- Default selected tile type is now "Hedge". 

## [1.1.0] - 2018-04-24
### Added
- Added name and version of program to window title.
- Added some padding to input elements.
- Added specific tile type colors to tile buttons.
- Added .json file extension filter and a generated file name to "Export" dialog.
- Added possibility to change amount of tile rows/columns (default is 8 x 8).
- Added amount of tile rows/columns to window title.
- Added more test methods.

### Changed
- Enclosed and outsourced level export function to "Level" class.
- Changed window width from 400 pixels to 500 pixels.
- Changed design of error messages to error messages with icon.
- Limited possible level time from 1 second to 120 seconds.

## 1.0.0 - 2018-04-17
### Added
- Initial release.

[Unreleased]: https://github.com/Pixelhash/webtech-level-builder/compare/v1.2.0...HEAD
[1.2.0]: https://github.com/Pixelhash/webtech-level-builder/compare/v1.1.0...v1.2.0
[1.1.0]: https://github.com/Pixelhash/webtech-level-builder/compare/v1.0.0...v1.1.0
