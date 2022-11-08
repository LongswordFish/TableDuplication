# This is the docker file for the front-end of deduplicate tables

#################################################################################
# Stage 0: base stage

# Use the 16.14-alpine3.14 version so the image will be smaller
FROM node:16.14-alpine3.14 AS base

LABEL maintainer="Jianchang Yu <jyu205@myseneca.ca>" \
      description="Deduplicate tables front UI"

WORKDIR /app

# copy dep files and install the production deps
COPY package*.json ./

# install the dependencies
RUN npm ci --only=production
#######################################################################

# use dependencies to build the site
FROM node:16.14-alpine3.14 AS builder

ENV NODE_ENV=production

WORKDIR /app
# Copy cached dependencies from previous stage so we don't have to download
COPY --from=base /app /app
# Copy source code into the image
COPY . .

RUN npm run build

########################################################################

# nginx web server to host the built site
FROM nginx:1.20.2 AS deploy

# Put our src/ into /usr/share/nginx/html/ and host static files
COPY --from=builder /app/build/ /usr/share/nginx/html/

EXPOSE 80

HEALTHCHECK --interval=30s --timeout=30s --start-period=5s --retries=3  CMD wget --no-verbose --tries=1 --spider localhost:80 || exit 1