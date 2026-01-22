<div align="center">

<img src="logos/logo.png" alt="" width="30%">

[![GitHub Repository](https://img.shields.io/badge/SourceCode-GitHub-blue?logo=github)](https://github.com/wunanc/JiMsg)
[![bStats](https://img.shields.io/badge/bStats-Statistics-eacd76?logo=google-analytics)](https://bstats.org/plugin/bukkit/JiMsg/28788)


[![Latest Build](https://img.shields.io/github/v/release/wunanc/JiMsg?label=LatestBuild&logo=github&color=0aa344)](https://github.com/wunanc/JiMsg/releases/latest)

[![Modrinth](https://img.shields.io/badge/To-Modrinth-1bd96a)](https://modrinth.com/plugin/lunadeer-dominion)
[![Spigot](https://img.shields.io/badge/To-Spigot-ed8106)](#)

![](https://img.shields.io/github/downloads/wunanc/JiMsg/total?logo=github&label=Github%20Downloads)

</div>

JiMsg is a plugin for modifying Minecraft server player join and quit messages, supporting Folia!

## Features

- Customize the message when players join the server
- Customize the message when players leave the server
- Supports `Paper|Folia` servers
- Uses bStats statistics (optional)
- Supports color codes and placeholders

## Installation

1. Download the JiMsg plugin JAR file
2. Put it in your server's [plugin](file:///H:/Wunanc/JiMsg/src/main/resources/plugin.yml#L1-L1) directory
3. Restart the server
4. Check the configuration file and customize as needed

## Configuration

The plugin generates a [config.yml](file:///H:/Wunanc/JiMsg/src/main/resources/config.yml#L1-L1) file upon first run, you can edit this file to customize join/quit messages.

## Dependencies

- Paper/Spigot 1.21 or higher
- Java 21+

## Development

This project uses Maven for building, you can compile the plugin with the following command:

```bash
mvn clean package
```

## Author

Hotguo

## License

This project is licensed under the MIT License - see the [LICENSE](file:///H:/Wunanc/JiMsg/LICENSE#L1-L21) file for details.

## Statistics

This plugin uses bStats to collect anonymous usage statistics to help improve the plugin.

![](https://bstats.org/signatures/bukkit/JiMsg.svg)