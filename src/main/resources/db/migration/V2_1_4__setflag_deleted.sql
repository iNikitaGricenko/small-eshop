UPDATE orders set deleted=true and deleted_at=now() where deleted is null;