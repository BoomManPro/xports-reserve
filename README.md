# Xports-Reserve

[xports-reserve 设计文档](https://epqx3fojfo.feishu.cn/docs/doccn6obAKTEEumH77XtD7d9b9e)

## 项目背景和目标
天通苑体育馆的羽毛球场地比较难定，感觉大家都在用脚本抢，为了公平起见，开发工具用于抢。

## 羽毛球场地预定流程图

![image](https://github.com/BoomManPro/xports-reserve/blob/main/docs/image/reserveFlow.png)

定/退场需知
系统每日8:00可预定第五天内场地  ==  当天可预定 T+4的场地

## 设计方案
### 核心接口

![image](https://github.com/BoomManPro/xports-reserve/blob/main/docs/image/reserveClient.png)

查询流程
1. 判断Cookie是否有效，如果有效则进行查询
2. 查询失败则Cookie失效，进行方糖推送
   预定接口
1. 获取四天后的日期
2. 判断Cookie有效，且是日期是在预定列表中
3. 进行预定，并将预定结果进行方糖推送

系统能力
1. 刷新Cookie有效期(请求场地列表页面)
2. 找到未预定场地，进行预定
3. 进阶：支持nacos远程配置cookie，在Cookie过期时手动介入更新，如果觉得部署麻烦，手动更新也可以项目nacos.enable=false
4. 进阶：支持xxl-job更新配置定时任务

![image](https://github.com/BoomManPro/xports-reserve/blob/main/docs/image/serveReserveFlow.png)

## Todo

| 序号 | 描述                                         | 状态 |
| ---- | -------------------------------------------- | ---- |
| 1    | 手动刷新Cookie，增加方糖推送                 | √    |
| 2    | 自动识别预定需求进行预定(限时预定，自动抢购) | √    |
| 3    |                                              |      |

