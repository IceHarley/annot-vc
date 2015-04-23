# annot-vc
Annotations version control. Collects revisions information from annotations across source code

### Usage
Add dependency `annot-vc-1.0-annotations.jar` to your project. Use annotations:

* `@Revision`
* `@History`

to set revision(s) to class or method. Revision should have `date` parameter and may have optional parameters: `authors` and `comment`.

Call `java -jar annot-vc-1.0.jar -scan <your_project.jar>` to scan all revisions across your project and save them to DB.

Call `java -jar annot-vc-1.0.jar -gui` or without arguments to open GUI. From GUI you could scan project and analyze all saved revisions.

### Examples


Sample project at GitHub: [avc-example](https://github.com/IceHarley/avc-example).

Several examples of possible annotations:

* Revised class with date only:

```
    @Revision(date = @Date(year = 2015, month = 4, day = 1)
    public class RevisedClass {}
```

* Revised class with date, author and comment:

```
    @Revision(
        date = @Date(year = 2015, month = 4, day = 1),
        authors = @Author("John"),
        comment = "RevisedClass implemented"
        )
    public class RevisedClass {}
```

* Class with history of its revisions:

```
    @History({
            @Revision(
                    date = @Date(year = 2015, month = 4, day = 6),
                    comment = "class implemented",
                    authors = {@Author("John"), @Author("Jack")}
            ),
            @Revision(
                    date = @Date(year = 2015, month = 4, day = 7),
                    comment = "class modified",
                    authors = {@Author("Jack"), @Author("Mike")}
            )
    })
    public class HistoryRevisedClass {}
```

* Revised method:

```
    @Revision(date = @Date(year = 2015, month = 4, day = 22))
    public void revisedMethod(Object... objects) {}
```

* History revised method:

```
        @History({
                @Revision(
                    date = @Date(year = 2015, month = 4, day = 17),
                    authors = @Author("Paul"),
                    comment = "algorithm implemented"),
                @Revision(
                    date = @Date(year = 2015, month = 4, day = 18),
                    authors = @Author("Paul"),
                    comment = "bug fixed")
        })
        public Integer historyRevisedMethod(String s) {return 0;}
```

### Requirements


* Java 1.7 or higher
* Used frameworks/libraries: spring, hibernate, cli, reflections, junit/hamcrest, hsqldb, JPA

### Algorithm


1.  Provided .jar is scanned for annotated classes/methods with `AnnotatedClassesSearcher`. 
    *   `AnnotatedClassesSearcher` is configured with set of scanners. For example `ClassesAnnotatedScanner` scans for revised classes, while `MethodsAnnotatedScanner` scans for revised methods
    *   It is possible to implement, inject and use more scanners to make `AnnotatedClassesSearcher` more flexible
    *   Output of `AnnotatedClassesSearcher` is list of classes which have at least one revision or revised method.
2.  List of annotated classes are transmitted to `RevisedSearcher`
    *   `RevisedSearcher` is configured with list of `RevisionsScanners`
    *   Each scanner process `@Revision` or `@History` annotation for class or method
    *   It is possible to implement and use more scanners to make `RevisedSearcher` more flexible
    *   Output of `RevisedSearcher` is `ClassesRevisedObjectsMap` - collection of revised objects grouped by classes
3.  `ClassesRevisedObjectsMap` is converted to persistence entities by `Converter`
    *   Output is list of Revision entities
    *   Each revision has links to corresponding class or method entity and authors entities
4.   Entities are persisted to DB
    *   Entities are annotated with hibernate annotations for automation of persisting
    *   There are repositories for each type of entities. Default CRUD operations are provided by spring, additional queries are configured with HQL
    *   Services use repositories and implement business rules. 
    For example if revision has same date and class/method with already existing revision, on save it will update that revision in DB. 
    Otherwise, it will be inserted as new revision. Thus you may update comment or author of already existing revision.
