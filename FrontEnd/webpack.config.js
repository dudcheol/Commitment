module.exports = {
    module: {
      rules: [
        {
          test: /\.styl$/,
          loader: "stylus-loader", // compiles Styl to CSS
        },
      ],
    },
  };