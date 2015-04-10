Move RevisedSearchers to their own package interactors.revisedsearchers
Rename PackageRevisedClassesSearcher to reflect it searches in classpath for given file
Rename ClassRevisedClassesSearcher to reflect it searches among given files and his derivatives
Move EmptyTestModel and TestModel to com.bikesandwheels - now there is no need to separate them into packages
Inject scanners into AnnotationsAnalizer
Rename AnnotationsAnalizer to RevisedObjectsSearcher
Move Scanners and Predicates to package scanners