const path = require('path');
const { injectBabelPlugin } = require('react-app-rewired');
const rewireLess = require('react-app-rewire-less-modules');

module.exports = function override(config, env) {
  config.resolve = {
    alias: {
      '@': path.resolve(__dirname, 'src'),
      components: path.resolve(__dirname, 'src/components'),
      assets: path.resolve(__dirname, 'src/assets')
    }
  };

  /*
  	npm start 的时候env= development
	npm run build 的时候 env= product
  */
  if (env === 'development') {
    config = injectBabelPlugin(['dva-hmr'], config);
  } else {
    config.output.publicPath = '/auth-client/'; // 跟据实际项目设置
    config.output.path = path.resolve(__dirname, '../../auth-client');
  }
  console.info("路径测试：",path.resolve(__dirname, '../../auth-client'));

  config = injectBabelPlugin('transform-decorators-legacy', config);
  config = injectBabelPlugin(
    ['import', { libraryName: 'antd', style: true }],
    config
  );

  config.externals = {};

  return rewireLess.withLoaderOptions(
    `${env === 'production' ? 'app' : '[local]'}-[hash:base64:8]`,
    {
      modifyVars: {}
    }
  )(config, env);
};
