+Move RevisedSearchers to their own package interactors.revisedsearchers
+Rename PackageRevisedClassesSearcher to reflect it searches in path
+Rename ClassRevisedClassesSearcher to reflect it searches among given files and his derivatives
+Move EmptyTestModel and TestModel to com.bikesandwheels - now there is no need to separate them into packages
+Rename AnnotationsAnalyzer to RevisedObjectsSearcher
+Move Scanners and Predicates to package scanners
+check ClassWithHistoryRevisionsScanner should return class history only!
+Inject scanners into RevisedObjectsSearcher
+Use scanners in RevisedClassesSearcher
TODO DB Schema:
    AVC_AUTHOR: AVC_AUT_ID, AVC_AUT_NAME
    AVC_CLASS: AVC_CLA_ID, AVC_CLA_CANONICAL_NAME
    AVC_METHOD: AVC_MET_ID, AVC_MET_NAME, AVC_MET_PARAMS_TYPES
    AVC_REVISION: (AVC_REV_CLASSID, AVC_REV_METHODID, AVC_REV_DATE), AVC_REV_COMMENT,
