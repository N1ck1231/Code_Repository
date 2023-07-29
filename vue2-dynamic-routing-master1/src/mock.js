const dynamicUser = [
    {
        name: "管理员",
        avatar: "https://sf3-ttcdn-tos.pstatp.com/img/user-avatar/ccb565eca95535ab2caac9f6129b8b7a~300x300.image",
        desc: "管理员 - admin",
        username: "liwen",
        password: "123",
        token: "rtVrM4PhiFK8PNopqWuSjsc1n02oKc3f",
        routes: [
            { id: 1, name: "/", path: "/", component: "Layout", redirect: "/index", hidden: false, children: [
                { name: "index", path: "/index", meta: { title: "index" }, component: "index/index" },
            ]},
            { id: 2, name: "/search", path: "/search", component: "Layout", redirect: "/search/index", hidden: false, children: [
                { name: "/search/index", path: "/search/index", meta: { title: "search" }, component: "search/index" }
            ]},
            { id: 3, name: "/table", path: "/table", component: "Layout", redirect: "/table/index", hidden: false, children: [
                { name: "/table/index", path: "/table/index", meta: { title: "table" }, component: "table/index" }
            ] },
        ]
    },
    {
        name: "普通用户",
        avatar: "https://seatmeat.oss-cn-shanghai.aliyuncs.com/BiaoZhu/boy.png",
        desc: "普通用户 - people",
        username: "`people`",
        password: "123456",
        token: "4es8eyDwznXrCX3b3439EmTFnIkrBYWh",
        routes: [
            { id: 1, name: "/", path: "/", component: "Layout", redirect: "/index", hidden: false, children: [
                { name: "index", path: "/index", meta: { title: "index" }, component: "index/index" },
            ]},
            { id: 2, name: "/search", path: "/search", component: "Layout", redirect: "/search/index", hidden: false, children: [
                { name: "/search/index", path: "/search/index", meta: { title: "search" }, component: "search/index" }
            ]},
        ]
    }
]

export default dynamicUser