# gradle-skip-when-empty 

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
