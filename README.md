# gradle-skip-when-empty 

**Update**: See [here](https://github.com/gradle/gradle/issues/9125#issuecomment-1144924302) for an explanation of what's going on here.

> There is a caveat though: Gradle only deletes the output if it is safe to delete. When the base plugin is applied, then everything in the build directory is deemed safe to delete. In your reproducer, if you apply the base plugin, then you can see the output files being deleted.

## Repro
+ Run `./gradlew compileTypeScript`
+ Inspect the `build` directory: 

```
> tree build
build
└── typescript
    └── index.js
```

+ Delete `src/index.ts`
+ Run `./gradlew compileTypeScript --info`

```
> Task :compileTypeScript NO-SOURCE
Skipping task ':compileTypeScript' as it has no source files and no previous output files.
:compileTypeScript (Thread[Execution worker for ':',5,main]) completed. Took 0.002 secs.
```

+ Inspect the `build` directory again:

```
> tree build
build
└── typescript
    └── index.js
```
