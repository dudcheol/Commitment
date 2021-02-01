module.exports = {
  devServer:{
    disableHostCheck:true,
    proxy: {
      '/': {
          target: 'http://인스턴스주소:3000',
            changeOrigin: true
        }
    }
  },
  "transpileDependencies": [
    "vuetify"
  ]
}