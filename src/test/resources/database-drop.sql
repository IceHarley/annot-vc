ALTER TABLE AVC_METHOD
DROP CONSTRAINT FK_AVC_MET_CLASSID;

ALTER TABLE AVC_REVISION
DROP CONSTRAINT FK_AVC_REV_CLASSID;

ALTER TABLE AVC_REVISION
DROP CONSTRAINT FK_AVC_REV_METHODID;

DROP TABLE AVC_AUTHOR
IF EXISTS;

DROP TABLE AVC_CLASS
IF EXISTS;

DROP TABLE AVC_METHOD
IF EXISTS;

DROP TABLE AVC_REVISION
IF EXISTS;