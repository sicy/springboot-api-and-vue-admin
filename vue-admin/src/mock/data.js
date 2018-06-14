let Menus = {
  success: true,
  msg: "",
  data: [
    {
      id: 10,
      menu_name: "系统管理",
      url: null,
      icon_class: null,
      chlid_list: [{
        id: 30,
        menu_name: "用户与权限管理",
        url: "/user",
        icon_class: "icon-users3",
      }]
    },
    {
      id: 11,
      menu_name: "资源审核",
      url: null,
      icon_class: null,
      chlid_list: [{
        id: 33,
        menu_name: "资源审核管理",
        url: "/videoInfo",
        icon_class: "icon-eye",
      }]
    },
    {
      id: 12,
      menu_name: "过滤规则",
      url: null,
      icon_class: null,
      chlid_list: [
        {
          id: 34,
          menu_name: "敏感词库管理",
          url: "/keyword",
          icon_class: "icon-file-text2"
        },
        {
          id: 344,
          menu_name: "敏感词详情",
          url: "/keyword/detail/:id",
          icon_class: "icon-file-text2",
          hidden: true
        },
        {
          id: 35,
          menu_name: "URL管理",
          url: "/urlManagement",
          icon_class: "icon-globe2"
        },
        {
          id: 37,
          menu_name: "图表",
          url: "/charts",
          icon_class: "icon-sphere"
        }
      ]
    }
  ]
};

export {Menus};
