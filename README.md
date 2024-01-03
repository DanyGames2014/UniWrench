# UniWrench
This mods adds a wrench which is meant to be intercompatible between any mod that implements it.

- By default it adds a "Universal Wrench" and 2 default wrench modes : Wrench and Rotate
- Mods can add their own wrench modes to either the default Universal Wrench or their own wrenches
- Mods can also extend the WrenchBase to make their own wrench which is compatible with other wrenches

## How to use it in my mod ?
First you need to import the mod into your gradle workspace by adding a dependency from [JitPack](https://jitpack.io/#DanyGames2014/UniversalWrench)

In build.gradle in dependencies add the following :
```
dependencies {
  modImplementation("com.github.DanyGames2014:UniWrench:${project.uniwrench_version}")
}
```

In gradle.properties (this is not needed but generally advised to configure your version in here)
```
uniwrench_version=1.2.0
```
### Adding your own wrench mode
To add your own wrench mode, listen to the `WrenchModeRegistryEvent` and create a new instance of `WrenchMode`.
Example :
```
public class WrenchModeListener {
    @Entrypoint.Namespace
    public static final Namespace MOD_ID = Null.get();

    public static WrenchMode MODE_EXAMPLE;

    @EventListener
    public void registerWrenchMode(WrenchModeRegistryEvent event) {
        MODE_EXAMPLE = new WrenchMode(MOD_ID.id("example"));
    }
}
```
To then localize the wrench mode you want to add a lang key in the format of `wrenchmode.<name>.name=Localized Name` into the lang file
Example
```
wrenchmode.example.name=Example
```

### Adding a wrench mode to the universal wrench
To add a mode to the Universal Wrench, listen to the `UniversalWrenchModeEvent`
Example :
```
public class UniversalWrenchModeListener {
    @EventListener
    public void addUniversalWrenchMode(UniversalWrenchModeEvent event){
        event.wrench.addWrenchMode(WrenchModeListener.MODE_EXAMPLE);
    }
}
```
