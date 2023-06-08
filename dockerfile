FROM google/cloud-sdk

ADD ./aerial-venture-386206-b11b4f81d05e.json /gcloud/config/aerial-venture-386206-b11b4f81d05e.json

RUN gcloud auth activate-service-account ci-cd-935@aerial-venture-386206.iam.gserviceaccount.com  --key-file=/gcloud/config/aerial-venture-386206-b11b4f81d05e.json --project=aerial-venture-386206

RUN gcloud container clusters get-credentials movie-app-cluster --zone us-west1-a --project aerial-venture-386206

RUN kubectl get pods
