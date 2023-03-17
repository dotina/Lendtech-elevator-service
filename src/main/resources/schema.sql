create table IF NOT EXISTS tbl_elevator (
	id int auto_increment,
	current_floor int default 0 not null,
	destination_floor int default 0 not null,
	direction varchar(70) null,
	is_moving boolean default false not null,
	elevator_door_state varchar(100) null,
	max_floor int default 3 not null,
    created_at  timestamp default CURRENT_TIMESTAMP,
    updated_at  timestamp default CURRENT_TIMESTAMP,
	constraint tbl_elevator_pk primary key (id)
);