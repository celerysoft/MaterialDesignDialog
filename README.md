# Material Design Dialog

## What is it?

Material Design Dialog is an Android dialog widget that accords with the Google Material Design.

Enabling you to use Material Design dialogs down to API 14

## Features

* Tiny size of lib, it is just a dialog, make it simple.

* Easy to ues

## Technical Information

* Required minimum API level: 14(Android 4.0)

* Supports all the screens sizes and density.

## Usage

### Step 1

#### Gradle

```
dependencies {
        compile 'com.celerysoft:materialdesigndialog:1.0.1'
}
```

### Step 2
```
String message = "This dialog use material-design to design it."
		+ " Use it if you really like it,"
		+ " make it better if you feel it suck."
        + "\nThis dialog has 2 themes and"
		+ " 2 styles, hope you can like it.";
final MaterialDesignDialog dialog = new MaterialDesignDialog(this)
		.setTitle("Material Design Dialog")
		.setMessage(message)
		.setCanceledOnTouchOutside(true)
		.setPositiveButton("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
dialog.show();
```

中国的开发者同仁，你可以在[这里](http://celerysoft.github.io/2015-12-30.html)查看更详细的使用说明。

## Screenshots

![Screenshot 0](https://raw.githubusercontent.com/celerysoft/README/master/MaterialDesignDialog/sc01.gif "Screenshot 0")

![Screenshot 1](https://raw.githubusercontent.com/celerysoft/README/master/MaterialDesignDialog/sc01.png "Screenshot 1")

![Screenshot 2](https://raw.githubusercontent.com/celerysoft/README/master/MaterialDesignDialog/sc02.png "Screenshot 2")

![Screenshot 3](https://raw.githubusercontent.com/celerysoft/README/master/MaterialDesignDialog/sc03.png "Screenshot 3")

## License

[MIT](./LICENSE)
