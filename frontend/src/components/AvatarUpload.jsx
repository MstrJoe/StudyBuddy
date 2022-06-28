import React from 'react';
import { useDropzone } from 'react-dropzone';
import classNames from 'classnames/bind';

export function AvatarUpload({ onDrop, avatar }) {
  const { getRootProps, getInputProps, isDragActive } = useDropzone({
    onDrop,
    accept: { 'image/*': ['.png', '.gif', '.jpeg', '.jpg'] },
    maxFiles: 1,
    maxSize: '2MB',
  });

  return (
    <div className={classNames('avatar-dropzone', avatar && 'has-image')} {...getRootProps()}>
      {avatar && <img className="avatar" src={avatar} alt="Avatar" />}
      <input {...getInputProps()} accept="image/*" />
      {isDragActive ? (
        <p>Drop the files here ...</p>
      ) : (
        <p>Drag 'n' drop your photo here, or click to select a file</p>
      )}
    </div>
  );
}
