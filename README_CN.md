<div align="center">

<img src="logos/logo.png" alt="" width="30%">

[![GitHub Repository](https://img.shields.io/badge/SourceCode-GitHub-blue?logo=github)](https://github.com/wunanc/JiMsg)
[![bStats](https://img.shields.io/badge/bStats-Statistics-eacd76?logo=google-analytics)](https://bstats.org/plugin/bukkit/JiMsg/28788)


[![Latest Build](https://img.shields.io/github/v/release/wunanc/JiMsg?label=LatestBuild&logo=github&color=0aa344)](https://github.com/wunanc/JiMsg/releases/latest)

[![Modrinth](https://img.shields.io/badge/To-Modrinth-1bd96a)](https://modrinth.com/plugin/lunadeer-dominion)
[![Spigot](https://img.shields.io/badge/To-Spigot-ed8106)](#)

![](https://img.shields.io/github/downloads/wunanc/JiMsg/total?logo=github&label=Github%20Downloads)

</div>

JiMsg 是一个用于修改 Minecraft 服务器玩家加入和退出消息的插件，支持 Folia！

## 特性

- 自定义玩家加入服务器时的消息
- 自定义玩家离开服务器时的消息
- 支持 `Paper|Folia` 服务端
- 使用 bStats 统计数据（可选）
- 支持颜色代码和占位符

## 安装

1. 下载 JiMsg 插件 JAR 文件
2. 将其放入您的服务器 [plugin](file:///H:/Wunanc/JiMsg/src/main/resources/plugin.yml#L1-L1) 目录中
3. 重启服务器
4. 检查配置文件并根据需要进行自定义

## 配置

插件会在第一次运行时生成 [config.yml](file:///H:/Wunanc/JiMsg/src/main/resources/config.yml#L1-L1)，您可以编辑该文件来自定义加入/退出消息。

## 依赖

- Paper/Spigot 1.21 或更高版本
- Java 21+

## 开发

本项目使用 Maven 构建，您可以通过以下命令编译插件：

```bash
mvn clean package
```

## 作者

Hotguo

## 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](file:///H:/Wunanc/JiMsg/LICENSE#L1-L21) 文件了解详情。

## 统计

本插件使用 bStats 来收集匿名使用统计数据，以帮助改进插件。

![](https://bstats.org/signatures/bukkit/JiMsg.svg)