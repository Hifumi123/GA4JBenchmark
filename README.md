# GA4J Benchmark

[GA4J](https://github.com/Hifumi123/GA4J) 的性能测试工程，使用了 [GA4J Examples](https://github.com/Hifumi123/GA4JExamples) 中的示例。

## 指标

### 在线性能

在环境 $e$ 下策略 $s$ 的在线性能 $X_e(s)$ 定义为：

$X_e(s) = \frac 1T \sum_{t=1}^T f_e(t)$

式中，$f_e(t)$ 是在环境 $e$ 下第 $t$ 时刻的平均适应度。

### 离线性能

在环境 $e$ 下策略 $s$ 的离线性能 $X_e^*(s)$ 定义为：

$X_e^*(s) = \frac 1T \sum_{t=1}^T f_e^*(t)$

式中，$f_e^*(t)$ 是在环境 $e$ 下 $[0, t]$ 时间段内最大的适应度。

## 开发环境

* Java 8
* Eclipse 2020-06 (4.16.0)
* Maven 3.6.3

## 依赖

依赖 [GA4J](https://github.com/Hifumi123/GA4J)、[GA4J Examples](https://github.com/Hifumi123/GA4JExamples) 和 [Apache Commons CSV](https://commons.apache.org/proper/commons-csv)，见 POM 文件。

## 更新记录

### 0.1.0

第一版，包含对应 GA4J 0.1.0 版本和 GA4J Examples 0.1.0 版本的性能测试。