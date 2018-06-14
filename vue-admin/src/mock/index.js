import Mock from 'mockjs'
import {Menus} from './data.js'

let mock = {
  bootstrap() {

    Mock.setup({
      timeout: '200-600'
    });

    Mock.mock('/vvvvv/api/menu/queryForLogin', Menus);

  }
};


export default mock;
